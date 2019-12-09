package top.au.study._2014_09.no4;

/**
 * description:
 *
 * 试题编号：	201409-4
 * 试题名称：	最优配餐
 * 时间限制：	1.0s
 * 内存限制：	256.0MB
 * 问题描述：
 * 问题描述
 * 　　栋栋最近开了一家餐饮连锁店，提供外卖服务。随着连锁店越来越多，怎么合理的给客户送餐成为了一个急需解决的问题。
 * 　　栋栋的连锁店所在的区域可以看成是一个n×n的方格图（如下图所示），方格的格点上的位置上可能包含栋栋的分店（绿色标注）或者客户（蓝色标注），有一些格点是不能经过的（红色标注）。
 * 　　方格图中的线表示可以行走的道路，相邻两个格点的距离为1。栋栋要送餐必须走可以行走的道路，而且不能经过红色标注的点。
 *
 *
 * 　　送餐的主要成本体现在路上所花的时间，每一份餐每走一个单位的距离需要花费1块钱。每个客户的需求都可以由栋栋的任意分店配送，每个分店没有配送总量的限制。
 * 　　现在你得到了栋栋的客户的需求，请问在最优的送餐方式下，送这些餐需要花费多大的成本。
 * 输入格式
 * 　　输入的第一行包含四个整数n, m, k, d，分别表示方格图的大小、栋栋的分店数量、客户的数量，以及不能经过的点的数量。
 * 　　接下来m行，每行两个整数xi, yi，表示栋栋的一个分店在方格图中的横坐标和纵坐标。
 * 　　接下来k行，每行三个整数xi, yi, ci，分别表示每个客户在方格图中的横坐标、纵坐标和订餐的量。（注意，可能有多个客户在方格图中的同一个位置）
 * 　　接下来d行，每行两个整数，分别表示每个不能经过的点的横坐标和纵坐标。
 * 输出格式
 * 　　输出一个整数，表示最优送餐方式下所需要花费的成本。
 * 样例输入
 * 10 2 3 3
 * 1 1
 * 8 8
 * 1 5 1
 * 2 3 3
 * 6 7 2
 * 1 2
 * 2 2
 * 6 8
 * 样例输出
 * 29
 * 评测用例规模与约定
 * 　　前30%的评测用例满足：1<=n <=20。
 * 　　前60%的评测用例满足：1<=n<=100。
 * 　　所有评测用例都满足：1<=n<=1000，1<=m, k, d<=n^2。可能有多个客户在同一个格点上。每个客户的订餐量不超过1000，每个客户所需要的餐都能被送到。
 *
 * createTime: 2019-12-09 12:04
 * @author au
 */
public class Main {




}
