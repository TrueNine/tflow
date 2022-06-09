import 'package:flutter/material.dart';

import 'home/home.dart';

void main(List<String> args) => runApp(const RunnerApp());

class RunnerApp extends StatelessWidget {
  const RunnerApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const Home();
  }
}
