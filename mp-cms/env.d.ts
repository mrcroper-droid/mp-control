/// <reference types="vite/client" />
declare module '*.vue' {
    import { ComponentOptions } from 'vue'
    const componentOptions: ComponentOptions
    export default componentOptions
}

declare module 'element-plus/dist/locale/zh-cn.mjs';

declare module "vuex" {
    export * from "vuex/types/index.d.ts";
    export * from "vuex/types/helpers.d.ts";
    export * from "vuex/types/logger.d.ts";
    export * from "vuex/types/vue.d.ts";
  }