/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2020，所有权利保留。
 * <p>
 * 项目名：	tank
 * 文件名：	Tank.java
 * 模块说明：
 * 修改历史：
 * 2020/6/10 - taozhi - 创建。
 */
package com.snail.tank;

import java.awt.*;
import java.util.Random;

/**
 * 坦克封装类
 *
 * @author taozhi
 */
public class Tank {
  // 起始位置
  private int x, y;
  // 坦克宽度高度
  public static final int WIDTH = ResourceMgr.badTankD.getWidth();
  public static final int HEIGHT = ResourceMgr.badTankD.getHeight();

  // 碰撞检测使用
  Rectangle rect = new Rectangle();

  private Random random = new Random();
  // 方向
  private Dir dir = Dir.DOWN;
  // 速度
  private static final int SPEED = 5;
  // 移动或静止
  private boolean moving = true;
  // 死活
  private boolean living = true;

  private Group group = Group.BAD;

  // 让Tank持有TankFrame 的引用
  private TankFrame tf;

  public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.group = group;
    this.tf = tf;

    rect.x = this.x;
    rect.y = this.y;
    rect.width = WIDTH;
    rect.height = HEIGHT;
  }

  public boolean isMoving() {
    return moving;
  }

  public void setMoving(boolean moving) {
    this.moving = moving;
  }

  public Dir getDir() {
    return dir;
  }

  public void setDir(Dir dir) {
    this.dir = dir;
  }

  /**
   * TANK 自己画自己
   *
   * @param g
   */
  public void paint(Graphics g) {
    if (!living) {
      // 注意，此处需要去除tank，否则依然会有内存泄漏问题
      tf.tanks.remove(this);
    }

    switch (this.dir) {
      case LEFT:
        g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
        break;
      case RIGHT:
        g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
        break;
      case UP:
        g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
        break;
      case DOWN:
        g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
        break;
      default:
        break;
    }
    move();
  }

  private void move() {
    if (!moving) return;

    switch (this.dir) {
      case LEFT:
        x -= SPEED;
        break;
      case RIGHT:
        x += SPEED;
        break;
      case UP:
        y -= SPEED;
        break;
      case DOWN:
        y += SPEED;
        break;
      default:
        break;
    }

    // 敌方坦克，随机开火
    if (this.group == Group.BAD && random.nextInt(100) > 95)
      this.fire();
    // 随机改变方向
    if (this.group == Group.BAD && random.nextInt(100) > 95) {
      randomDir();
    }
    // 边界检测
    boundsCheck();
    // update rect
    rect.x = this.x;
    rect.y = this.y;
  }

  private void boundsCheck() {
    if (this.x < 2) x = 2;
    // 菜单栏
    if (this.y < 28) y = 28;
    if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
    if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
  }

  private void randomDir() {
    this.dir = Dir.values()[random.nextInt(4)];
  }

  public void fire() {
    int x_index = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
    int y_index = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
    tf.bullets.add(new Bullet(x_index, y_index, this.dir, this.group, tf));
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void die() {
    this.living = false;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }
}
