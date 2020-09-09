/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2020，所有权利保留。
 * <p>
 * 项目名：	tank
 * 文件名：	FrameTank.java
 * 模块说明：
 * 修改历史：
 * 2020/6/10 - taozhi - 创建。
 */
package com.snail.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author taozhi
 */
public class TankFrame extends Frame {

  Tank myTank = new Tank(200, 200, Dir.DOWN, Group.GOOD,this);
  List<Bullet> bullets = new ArrayList<>();
  List<Tank> tanks = new ArrayList<>();
  List<Explode> explodes = new ArrayList<>();
  Explode e = new Explode(100,100, this);
  static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;

  public TankFrame() {
    setSize(GAME_WIDTH, GAME_HEIGHT);
    setResizable(false);
    setTitle("坦克大战");
    setVisible(true);

    // 增加键盘监听事件
    this.addKeyListener(new MyKeyListener());

    // 增加关闭按钮事件
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) { // bjmashibing/tank
        System.exit(0);
      }

    });
  }

  // 解决双缓冲 闪烁问题
  Image offScreenImage = null;
  @Override
  public void update(Graphics g) {
    if(offScreenImage == null) {
      offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
    }
    Graphics gOffScreen = offScreenImage.getGraphics();
    Color c = gOffScreen.getColor();
    gOffScreen.setColor(Color.BLACK);
    gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
    gOffScreen.setColor(c);
    paint(gOffScreen);
    g.drawImage(offScreenImage, 0, 0, null);
  }

  @Override
  public void paint(Graphics g) {
    Color c = g.getColor();
    g.setColor(Color.WHITE);
    g.drawString("子弹的数量：" + bullets.size(), 10, 60);
    g.drawString("坦克的数量：" + tanks.size(), 10, 80);
    g.drawString("爆炸的数量：" + explodes.size(), 10, 100);
    g.setColor(c);

    myTank.paint(g);
    // 画地方坦克
    for (int i = 0; i < tanks.size(); i++) {
      tanks.get(i).paint(g);
    }

    // 为了集合元素删除，此处不能用增强for循环
    for (int i = 0; i < bullets.size(); i++) {
      bullets.get(i).paint(g);
    }

    // 画爆炸效果
    for (int i = 0; i < explodes.size(); i++) {
      explodes.get(i).paint(g);
    }

    // 碰撞检测，判断子弹和坦克是否相交
    for (int i = 0; i < bullets.size(); i++) {
      for (int j = 0; j < tanks.size(); j++) {
        bullets.get(i).collideWith(tanks.get(j));
      }
    }
  }

  class MyKeyListener extends KeyAdapter {

    // 标记坦克运动的方向状态
    boolean bL = false;
    boolean bU = false;
    boolean bR = false;
    boolean bD = false;

    @Override
    public void keyPressed(KeyEvent e) {

      int keyCode = e.getKeyCode();
      switch (keyCode) {
        case KeyEvent.VK_LEFT:
          bL = true;
          break;
        case KeyEvent.VK_UP:
          bU = true;
          break;
        case KeyEvent.VK_RIGHT:
          bR = true;
          break;
        case KeyEvent.VK_DOWN:
          bD = true;
          break;

        case KeyEvent.VK_CONTROL:
          // 打出一颗子弹
          myTank.fire();
          break;

        default:
          break;
      }
      setMainTankDir();
    }

    private void setMainTankDir() {
      if (!bL && !bU && !bR && !bD) {
        myTank.setMoving(false);
      } else {
        new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        myTank.setMoving(true);
        if (bL) myTank.setDir(Dir.LEFT);
        if (bR) myTank.setDir(Dir.RIGHT);
        if (bU) myTank.setDir(Dir.UP);
        if (bD) myTank.setDir(Dir.DOWN);
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      int keyCode = e.getKeyCode();
      switch (keyCode) {
        case KeyEvent.VK_LEFT:
          bL = false;
          break;
        case KeyEvent.VK_UP:
          bU = false;
          break;
        case KeyEvent.VK_RIGHT:
          bR = false;
          break;
        case KeyEvent.VK_DOWN:
          bD = false;
          break;

        default:
          break;
      }
      setMainTankDir();
    }
  }
}


