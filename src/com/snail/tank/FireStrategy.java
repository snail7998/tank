/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2020，所有权利保留。
 * <p>
 * 项目名：	tank
 * 文件名：	FireStrategy.java
 * 模块说明：
 * 修改历史：
 * 2020/9/9 - taozhi - 创建。
 */
package com.snail.tank;

/**
 * @author taozhi
 */
public interface FireStrategy {

  void fire(Tank tank, TankFrame tf);
}
