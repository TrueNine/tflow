<template>
  <div class="bgimg">
    <q-toolbar class="shadow-2 bg-primary text-white">
      <q-toolbar-title>Home By Data</q-toolbar-title>
      <q-btn @click="closeWindow" color="negative" icon="close" />
    </q-toolbar>
    <div class="logo-box">
      <img
        style="width: 12.5%; height: 12.5%"
        src="src/assets/quasar-logo-vertical.svg"
        alt="logo"
      />
      <img
        style="width: 12.5%; height: 12.5%"
        src="src/assets/logo-vue.png"
        alt="logo-vue"
      />
    </div>
    <div class="logo-box">
      <h2>TN 数据治理平台</h2>
    </div>
    <div class="logo-box">
      <q-btn-group>
        <q-btn @click="toBe(`/doc`)" color="primary" icon="edit"
          >MarkDown预览编辑器
        </q-btn>
        <q-btn @click="toBe(`/new`)" color="primary" icon="ion-logo-facebook"
          >新功能
        </q-btn>
        <q-btn @click="toBe(`/actuator`)" color="primary" icon="terminal"
          >监控面板
        </q-btn>
        <q-btn @click="fullScreen" color="primary" icon="fullscreen"
          >进入全屏模式
        </q-btn>
      </q-btn-group>
    </div>
    <div class="shadow-14 glass-bg-box">
      <h1>231222222222222</h1>
    </div>
  </div>
</template>

<script setup lang="ts">
  import { useRouter } from "vue-router";

  import { SockJs } from "@api/s/WebSocketInclude";
  import Stomp from "stompjs";
  import axios from "axios";
  import { ref } from "vue";

  const megs = ref<string[]>([]);
  const closeWindow = () => {
    window.location.href = "about:blank";
    window.close();
    window.open("about:blank", "_self");
  };

  const fullScreen = () => {
    document.documentElement.requestFullscreen();
  };

  axios.get("http://localhost:8080/sub").then((d) => {
    console.log("ws 已触发订阅");
  });

  const sock = new SockJs("http://localhost:8080/ws");
  const client = Stomp.over(sock);

  client.connect(
    {},
    (frame) => {
      console.log("打开连接  = " + frame);
      console.log(frame?.body);

      client.subscribe("/topic/subscribeTopic", (msg) => {
        console.log("订阅消息接受");
        console.log(msg.body);
      });
      client.subscribe("/topic/broadcast", (msg) => {
        megs.value.push(msg.body);
      });
    },
    (error) => {
      console.log("发生错误  =  " + error.toString());
    }
  );

  const router = useRouter();
  const toBe = (path: string) => {
    router.push(path);
  };
</script>

<style scoped lang="scss">
  .bgimg {
    background: url("src/assets/idea.svg") no-repeat top;
    background-size: contain;
    width: 100%;
    height: 100vh;
  }

  .logo-box {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;

    font-weight: bold;
  }

  .glass-bg-box {
    width: 50vw;
    display: flex;
    justify-content: center;

    backdrop-filter: blur(10px);
    box-shadow: #1d1d1d;
  }
</style>
