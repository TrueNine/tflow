export type IndexTypes = {
  href: string;
  templated: boolean;
};

/**
 * actuator 链接表
 */
export interface Index {
  _links: {
    beans: IndexTypes;
    health: IndexTypes;
    info: IndexTypes;
  };
}

export type EnvPropertiesNamed =
  | "server.ports"
  | "systemProperties"
  | "applicationConfig"
  | "servletContextInitParams"
  | "systemEnvironment"
  | "springCloudClientHostInfo"
  | "springCloudDefaultProperties";

export type EnvProperties = {
  name: EnvPropertiesNamed | `${string}`;
  properties: Record<string, { value: string }>;
};

/**
 * actuator env
 */
export interface Env {
  activeProfiles: string[];
  propertySources: EnvProperties[];
}

export type BeansContextMap = Record<
  string,
  {
    aliases: string[];
    scope: string;
    type: string;
    resource: string | null;
    dependencies: string[];
  }
>;
export type BeansContext = {
  parentId: string;
  beans: BeansContextMap;
};

/**
 * actuator Spring beans
 */
export interface Beans {
  contexts: BeansContext;
}
