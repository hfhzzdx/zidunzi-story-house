# CI 要求与注意事项（GitHub Actions / Gradle 8.5）

- 本地无编译环境：一切构建以 GitHub Actions 为准。
- Gradle 版本锁定：8.5（gradle-wrapper.properties 中 distributionUrl 必须指向 8.5）。
- JDK：17（Temurin）。
- Android 构建工具：Gradle 插件与 AGP 需与 8.5 兼容（建议 AGP 8.2.x/8.3.x）。
- 工作流步骤：
  1) 校验 Gradle Wrapper 版本（非 8.5 则失败）
  2) assembleRelease 打包
  3) 可选签名（Secrets 提供 keystore）
  4) 上传 APK Artifact

- 必配 Secrets（如需签名）：
  - KEYSTORE_BASE64 / KEYSTORE_PASSWORD / KEY_PASSWORD / KEY_ALIAS

- 常见问题：
  - 若 Wrapper 版本不符：更新 gradle/wrapper/gradle-wrapper.properties。
  - 若依赖下载缓慢：可增加 Gradle 缓存与镜像仓库配置。
