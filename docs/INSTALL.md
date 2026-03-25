# 安装与部署指南（草案）

一、获取 APK
- 方法A：GitHub Actions 自动构建
  1) Fork/创建仓库并推送代码
  2) 进入 Actions，手动运行 Android CI 或等待 push 触发
  3) 在运行详情 → Artifacts 下载 app-release-apk
- 方法B：签名发布（可选）
  - 在仓库 Settings → Secrets 添加：
    - KEYSTORE_BASE64：将 keystore 二进制用 base64 编码后的文本
    - KEYSTORE_PASSWORD / KEY_PASSWORD / KEY_ALIAS
  - 触发工作流后，产物将为已签名 APK

二、Android 安装指引
- Android 4.4~7：设置 → 安全 → 启用“未知来源”后安装
- Android 8~12：从文件管理器打开 APK，按提示允许安装来源
- Android 13+：首次运行时允许通知与媒体权限

三、首次使用
- 预置内容：应用内置≥300篇故事，首页按适龄与主题推荐
- 导入故事：
  - txt：将文件拷贝到设备存储，应用内“导入”选择文件
  - epub：同上，支持章节/插图解析
- 语音播放：首次使用如提示安装/下载离线语音包，按系统引导完成
- 家长区：在设置中开启，配置在线源与生成内容审核

四、常见问题
- TTS 声音偏快/慢：在设置中调节语速/音高
- 老设备卡顿：关闭插画自动轮播或降低插图分辨率
- 无法联网拉取内容：检查在线源地址是否正确、是否支持 TLS 老版本

五、升级与数据保留
- 升级不会清除已导入故事与阅读进度；建议定期备份应用数据

六、构建环境（仅供维护者）
- JDK 17 + Gradle；Android SDK target 35；minSdk 19
- 本地构建：./gradlew assembleRelease
