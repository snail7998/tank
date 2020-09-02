/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2020，所有权利保留。
 * <p>
 * 项目名：	tank
 * 文件名：	Bullet.java
 * 模块说明：
 * 修改历史：
 * 2020/6/10 - taozhi - 创建。
 */
package com.snail.tank;

import java.awt.*;

/**
 * @author taozhi
 */
public class Bullet {

  // 速度
  private static final int SPEED = 10;
  // 子弹外形
  public static final int WIDTH = ResourceMgr.bulletD.getWidth();
  public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

  // 位置
  private int x, y;
  // 方向
  private Dir dir;
  // 子弹是否在屏幕内，不在的话，从list中去除，保证内存不泄露
  private boolean living = true;

  private Group group = Group.BAD;

  private TankFrame tf;

  public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.group = group;
    this.tf = tf;
  }

  public void paint(Graphics g) {
    if (!living) {
      tf.bullets.remove(this);
    }

    switch (this.dir) {
      case LEFT:
        g.drawImage(ResourceMgr.bulletL, x, y, null);
        break;
      case RIGHT:
        g.drawImage(ResourceMgr.bulletR, x, y, null);
        break;
      case UP:
        g.drawImage(ResourceMgr.bulletU, x, y, null);
        break;
      case DOWN:
        g.drawImage(ResourceMgr.bulletD, x, y, null);
        break;
      default:
        break;
    }

    move();
  }

  private void move() {
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
    // 如果超出屏幕范围，子弹 live为 false
    if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
      living = false;
    }
  }

  /*
    碰撞检测
   */
  public void collideWith(Tank tank) {
    // 如果子弹和坦克都是好的，即一波的，则不撞
    if (this.group == tank.getGroup()) return;
    // TODO new Rectangle 太多，优化
    Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
    Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
    if (rect1.intersects(rect2)) {
      tank.die();
      this.die();
      tf.explodes.add(new Explode(tank.getX(),tank.getY(), tf));
    }

  }

  private void die() {
    this.living = false;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }
}
