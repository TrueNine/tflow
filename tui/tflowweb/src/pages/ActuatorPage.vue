<template>
  <q-bar>
    <q-btn @click="$router.push(`/`)">返回</q-btn>
  </q-bar>

  <div style="padding: 4rem">
    <!-- 系统信息 -->
    <div class="col">
      <div class="row justify-around">
        <div class="col">
          <span class="text-h3"
            >{{ info.sys.name }}
            <q-badge align="top">{{ info.sys.hardwarePlatform }}</q-badge>
          </span>

          <q-chip
            icon="home"
            color="primary"
            text-color="white"
            :label="info.sys.lang"
          />

          <q-chip
            color="primary"
            text-color="white"
            icon="alarm"
            :label="info.sys.zone"
          />

          <q-chip icon="window" color="red" text-color="white">
            {{ info.java.name + "  " + info.java.version }}
          </q-chip>
        </div>
        <q-chip
          :icon="upInfo.iconName"
          :text-color="upInfo.tColor"
          :color="upInfo.bgColor"
          >{{ upInfo.tips }}
        </q-chip>
      </div>
    </div>

    <!-- beans -->
    <div class="col text-center">
      <q-bar>
        <div class="col text-center">仪表板</div>
      </q-bar>
    </div>
  </div>
</template>
<script setup lang="ts">
  import { ActuatorReq } from "app/api/actuator/ActuatorReq";
  import { ref } from "vue";
  import { actuatorVo, ActuatorVo } from "app/api/actuator/ActuatorVo";

  const upInfo = ref({
    iconName: "ion-add",
    bgColor: "gray",
    tColor: "dark",
    tips: "LOADING...",
  });
  const isUp = ref(false);

  const info = ref<ActuatorVo>(actuatorVo);

  const loopPingFun = setInterval(() => {
    ActuatorReq.ping()
      .then(() => {
        isUp.value = true;
        upInfo.value.iconName = "upload";
        upInfo.value.bgColor = "green";
        upInfo.value.tColor = "white";
        upInfo.value.tips = "UP";
      })
      .catch(() => (isUp.value = false));
  }, 5000);
  // clearInterval(loopPingFun);

  ActuatorReq.ping().then(() => {
    ActuatorReq.env().then((d) => {
      // java
      d.propertySources
        .filter((s) => s.name === "systemProperties")
        .forEach((d) => {
          info.value.sys.hardwarePlatform = d.properties["os.arch"].value;
          info.value.sys.name = d.properties["os.name"].value;
          info.value.sys.lang = `${d.properties[
            "user.language"
          ].value.toUpperCase()}-${d.properties[
            "user.country"
          ].value.toUpperCase()}`;
          info.value.sys.zone = d.properties["user.timezone"].value;
          info.value.java.name = d.properties["java.vm.name"].value;
          info.value.java.version = d.properties["java.vm.version"].value;
        });
    });

    ActuatorReq.beans().then((d) => {
      if (info.value.springBeans) {
        info.value.springBeans.beans = d;
        console.error(d.contexts["application-1"].beans["classLoaderMetrics"]);
      }
    });
  });
</script>

<style lang="scss" scoped></style>
