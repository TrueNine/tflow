import axios from "axios";
import { Beans, Env, Index } from "app/api/actuator/ActuatorIndexDMO";

export const ActuatorBaseUrl = "http://localhost:8080/actuator";

const req = axios.create({ baseURL: ActuatorBaseUrl });
export const ActuatorUris = {
  index: "/",
  env: "/env",
  health: "/health",
  beans: "/beans",
};

export const ActuatorReq = {
  /**
   * 心跳测试
   */
  ping: async () =>
    req
      .get<boolean>(ActuatorUris.health)
      .then(() => true)
      .catch(() => false),
  /**
   * 链接表
   */
  index: async () => req.get<Index>(ActuatorUris.index).then((d) => d.data),
  /**
   * 环境
   */
  env: async () => req.get<Env>(ActuatorUris.env).then((d) => d.data),
  /**
   * springBeans
   */
  beans: async () => req.get<Beans>(ActuatorUris.beans).then((d) => d.data),
};
