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

/**
 * 坦克封装类
 *
 * @author taozhi
 */
public class Tank {
  // 起始位置
  private int x, y;
  // 方向
  private Dir dir = Dir.DOWN;
  // 速度
  private static final int SPEED = 7;
  // 移动或静止
  private boolean moving = false;

  // 让Tank持有TankFrame 的引用
  private TankFrame tf;

  public Tank(int x, int y, Dir dir, TankFrame tf) {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.tf = tf;
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
   * @param g
   */
  public void paint(Graphics g) {
    switch (this.dir) {
      case LEFT:
        g.drawImage(ResourceMgr.tankL, x, y, null);
        break;
      case RIGHT:
        g.drawImage(ResourceMgr.tankR, x, y, null);
        break;
      case UP:
        g.drawImage(ResourceMgr.tankU, x, y, null);
        break;
      case DOWN:
        g.drawImage(ResourceMgr.tankD, x, y, null);
        break;
      default:
        break;
    }
    move();
  }

  private void move() {
    if(!moving) return;

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
  }

  public void fire() {
    tf.bullets.add(new Bullet(this.x, this.y, this.dir, tf));
  }
}
