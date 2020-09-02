/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2020，所有权利保留。
 * <p>
 * 项目名：	tank
 * 文件名：	Main.java
 * 模块说明：
 * 修改历史：
 * 2020/6/10 - taozhi - 创建。
 */
package com.snail.tank;

/**
 * @author taozhi
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {
    TankFrame tf = new TankFrame();

    // 初始化敌方坦克
    for (int i=0; i<5; i++) {
      tf.tanks.add(new Tank(100 + i*60, 100, Dir.DOWN, Group.BAD, tf));
    }

    while(true) {
      Thread.sleep(100);
      tf.repaint();
    }
  }
}
