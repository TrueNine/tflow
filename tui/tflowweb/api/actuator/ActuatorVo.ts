import { Beans } from "app/api/actuator/ActuatorIndexDMO";

export type JavaInfo = {
  name: string;
  version: string;
};

export type ActuatorVo = {
  sys: {
    name: string;
    softwarePlatform: string;
    hardwarePlatform: string;
    lang: `${string}-${string}`;
    zone: string;
  };
  java: JavaInfo;
  springBeans?: {
    beans?: Beans;
  };
};

export const actuatorVo: ActuatorVo = {
  sys: {
    lang: "en-US",
    name: "",
    softwarePlatform: "",
    hardwarePlatform: "",
    zone: "",
  },
  java: {
    version: "",
    name: "",
  },
  springBeans: {
    beans: undefined,
  },
};
