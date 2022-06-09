import 'package:flutter/material.dart';
import 'package:platform_device_id/platform_device_id.dart';

class CenterHome extends StatelessWidget {
  const CenterHome({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        children: const [MyState()],
      ),
    );
  }
}

class MyState extends StatefulWidget {
  const MyState({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    return _MyStateState();
  }
}

class _MyStateState extends State<MyState> {
  int _num = 0;
  String _deviceId = "";

  void _increment() {
    setState(() {
      _num += 1;
    });
  }

  void _decrement() {
    setState(() {
      _num -= 1;
    });
  }

  void _getDeviceId() {
    print("click");
  }

  @override
  void initState() {
    super.initState();
    print("触发一次");
    PlatformDeviceId.getDeviceId.then((value) {
      _deviceId = value.toString();
      print("已经改变");
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Row(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: _num >= 30 ? null : _increment,
              child: const Icon(Icons.plus_one),
              style: ButtonStyle(backgroundColor: SuccessStateColor()),
            ),
            Padding(
              padding: const EdgeInsets.all(20),
              child: Text("Number Of $_num"),
            ),
            ElevatedButton(
                style: ButtonStyle(backgroundColor: ErrorStateColor()),
                onPressed: _num <= 0 ? null : _decrement,
                child: const Icon(Icons.exposure_minus_1))
          ],
        ),
        Text("Agent = $_deviceId"),
        Row(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            TextButton(
                onPressed: _getDeviceId,
                child: const Icon(Icons.panorama_fisheye))
          ],
        )
      ],
    );
  }
}

class SuccessStateColor extends MaterialStateProperty<Color?> {
  @override
  Color? resolve(Set<MaterialState> states) {
    if (states.contains(MaterialState.disabled)) {
      return Colors.black26;
    }
    return Colors.green;
  }
}

class ErrorStateColor extends MaterialStateProperty<Color?> {
  @override
  Color? resolve(Set<MaterialState> states) {
    if (states.contains(MaterialState.disabled)) {
      return Colors.black26;
    }
    return Colors.red;
  }
}
