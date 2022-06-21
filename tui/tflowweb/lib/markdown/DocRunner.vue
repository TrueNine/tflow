<template>
  <!-- 顶层 -->
  <div class="t-markdown">
    <!-- 编辑器 -->
    <div class="pad-aero">
      <div class="pad">
        <div class="earpiece"></div>
        <div class="screen">
          <!-- 工具栏 -->
          <div class="editor-tool-bar">
            <span>
              <q-icon name="edit" size="1.5rem" />
              <q-icon name="home" size="1.5rem" />
              <q-icon name="car" size="1.5rem" />
            </span>
            <span>
              <q-icon name="wifi" size="1.5rem" />
              <q-icon name="code" size="1.5rem" />
            </span>
          </div>
          <div style="text-align: center; background-color: white; border-bottom: 1px solid #888888ff">
            <strong>编辑区域</strong>
          </div>
          <textarea v-model="markdownCode"></textarea>
        </div>
        <!-- 底部按钮 -->
        <div class="btn-bar">
          <div class="btn">
            <div />
          </div>
        </div>
      </div>
    </div>
    <!-- 预览器 -->
    <div class="view">
      <div class="markdown-view" v-html="view"></div>
    </div>
  </div>
</template>

<style scoped lang="scss">
  @use "../../src/css/quasar.variables" as *;

  .t-markdown {
    display: flex;
    flex-direction: row;
    width: 100vw;
    height: 100vh;

    .pad-aero {
      height: 100%;
      width: 40%;

      display: flex;
      justify-content: center;
      align-items: center;

      .pad {
        height: 80%;
        width: 80%;
        border-radius: 1rem;
        background-color: #cccccc;
        padding-bottom: 4rem;

        .earpiece {
          height: 0.125rem;
          width: 2rem;
          background-color: $dark;
          border-radius: 1px;
          margin-top: 0.5rem;
          position: relative;
          margin-inline: 50%;
        }

        .screen {
          display: flex;
          flex-direction: column;
          min-width: 35%;
          height: 100%;

          padding-top: 1rem;
          padding-inline: 1rem;

          .editor-tool-bar {
            display: flex;
            justify-content: space-between;
            padding-inline: 1rem;
            background-color: lighten($dark, 5);

            cursor: pointer;
            width: 100%;
            min-height: 2rem;
            box-sizing: border-box;
            color: white;
          }

          textarea {
            color: $dark;
            font-size: 1.5rem;
            box-sizing: border-box;

            border: none;
            outline: none;

            resize: none;
            width: 100%;
            height: 100%;

            &:focus {
              border: none;
            }
          }
        }

        .btn-bar {
          display: flex;
          padding-top: 0.5rem;
          justify-content: center;
          align-items: center;

          .btn {
            display: flex;
            justify-content: center;
            align-items: center;

            background-color: white;
            border-radius: 50%;
            height: 2rem;
            width: 2rem;

            div {
              background-color: $dark;
              height: 0.375rem;
              width: 0.375rem;
              border-radius: 50%;
            }
          }
        }

        box-shadow: 1.4px 1.3px 1.7px rgba(0, 0, 0, 0.08), 3.3px 3.1px 4.2px rgba(0, 0, 0, 0.058), 6.3px 5.8px 7.9px rgba(0, 0, 0, 0.048),
          11.2px 10.3px 14.1px rgba(0, 0, 0, 0.04), 20.9px 19.2px 26.3px rgba(0, 0, 0, 0.032), 50px 46px 63px rgba(0, 0, 0, 0.022);
      }
    }

    .view {
      box-sizing: border-box;
      border-left: 1px solid lighten($primary, 40);
      width: 60%;
      height: 100%;
      overflow-y: auto;
    }
  }
</style>

<script setup lang="ts">
  import "./tcode.scss";
  import MarkDown from "markdown-it";

  import {ref, watch} from "vue";
  import hijs from "./InjectLang";

  const md = new MarkDown({
    html: true,
    highlight: (code, lang) => {
      if (lang && hijs.getLanguage(lang)) {
        try {
          return hijs.highlightAuto(code).value;
        } catch (e) {
          console.warn(e);
        }
      }
      return `<pre><code class="language-text">${code}</code></pre>`;
    }
  });

  const markdownCode = ref("");
  const view = ref("");

  watch(
    () => markdownCode.value,
    () => {
      view.value = md.render(markdownCode.value);
    }
  );
</script>
