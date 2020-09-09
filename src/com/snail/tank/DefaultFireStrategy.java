/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2020，所有权利保留。
 * <p>
 * 项目名：	tank
 * 文件名：	DefaultFireStrategy.java
 * 模块说明：
 * 修改历史：
 * 2020/9/9 - taozhi - 创建。
 */
package com.snail.tank;

/**
 * @author taozhi
 */
public class DefaultFireStrategy implements FireStrategy {

  @Override
  public void fire(Tank tank, TankFrame tf) {
    int x_index = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
    int y_index = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
    tf.bullets.add(new Bullet(x_index, y_index, tank.dir, tank.group, tf));

    if(tank.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
  }
}
