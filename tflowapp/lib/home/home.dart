import 'package:flutter/material.dart';
import 'package:tflowapp/home/center_home.dart';


class Home extends StatelessWidget {
  const Home({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      title: "TFlow",
      home: Page(),
    );
  }
}

/// # 主要框架
/// 它主要包含一个 appbar
class Page extends StatelessWidget {
  const Page({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("access"),
        centerTitle: true,
        leading: const Icon(Icons.arrow_back_ios_new),
      ),
      body: const CenterHome(),
    );
  }
}
