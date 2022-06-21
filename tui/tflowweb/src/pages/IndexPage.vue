<template>
  <div class="bgimg">
    <q-bar>Application</q-bar>
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
      <h2>True_Nine 数据治理平台</h2>
    </div>
    <div class="logo-box">
      <q-btn @click="toBe(`/doc`)" color="primary">MarkDown预览编辑器</q-btn>
      <q-btn @click="toBe(`/new`)" color="green">新功能</q-btn>
      <q-btn @click="toBe(`/actuator`)" color="orange">监控面板</q-btn>
      <!-- 测试 -->
      <div>
        <q-input v-model="msg" />
        <q-btn @click="sendMsg" color="primary">Send Message</q-btn>
        <div
          style="background-color: white"
          v-for="(item, index) in megs"
          :key="index"
        >
          {{ item }}
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
  import { useRouter } from "vue-router";

  import { SockJs } from "@api/s/WebSocketInclude";
  import Stomp from "stompjs";
  import axios from "axios";
  import { ref } from "vue";

  const msg = ref<string>("");
  const megs = ref<string[]>([]);

  axios.get("http://localhost:8080/sub").then((d) => {
    console.log("ws 已触发订阅");
  });

  const sock = new SockJs("http://localhost:8080/ws");
  const client = Stomp.over(sock);

  const sendMsg = () => {
    client.send("/sendToTopic", {}, msg.value);
  };

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
</style>
