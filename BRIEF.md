# JJC-20260325-001 · 儿童故事应用设计与云端构建（项目简报｜已按拍板更新）

一、目标与范围
- 面向对象：3-8岁儿童与监护人
- 应用名称：小墩子故事屋（Child-themed 视觉风格）
- 应用类型：儿童故事类应用（文字/插画/语音/音频播放）
- 运行平台：Android（minSdk 暂定 24，targetSdk 35；如需改为 26 请批示）
- 交付物：
  1) 可安装 APK（Release 构建由 GitHub Actions 产出）
  2) 自带≥300篇可用故事（含分类/标签/适龄信息，避免大面积重复）
  3) 功能完整的播放器（后台播放/进度/定时/语音TTS/插画页轮播）
  4) 支持导入 txt/epub
  5) 支持从网络获取或由 agent 自动生成并入库（无需人工审核）
  6) 安装与部署文档（仅简体中文）

二、核心功能清单
- 内容
  - 本地预置≥300篇故事，结构化元数据（标题、作者/来源、适龄、主题标签、正向价值观标签、章节、插图）
  - 导入：txt、epub（解析章节/段落、插图、封面）
  - 在线：可配置的内容提供器（HTTP JSON/静态清单/GitHub Pages/CDN），断网可用；无需源白名单
  - 生成：agent 端点生成故事（自动上线，无需审核；内置正向价值观生成约束）
- 播放与阅读
  - 背景播放（前台服务 + MediaSession），播放/暂停/上一下一/进度拖拽
  - 定时播放（睡眠定时器，结束时渐弱/暂停）
  - 语音播放：Android TTS（系统引擎，支持离线语音包；语速/音高可调）
  - 插画播放：按段落/页轮播插图，支持自动翻页与手势
  - 继续播放/阅读进度记忆（按故事/章节记录）
- 适龄与守护
  - 适龄分级（3-5/6-8/混龄），首页按适龄与主题推荐
  - 正向价值观标签（善良/勇气/合作/环保/诚实等）
  - 家长区：导入/在线源设置；生成内容默认自动上线（无审核）
- 导入/存储
  - 解析：txt（编码/段落/封面推断）、epub（OPF/NCX/资源表）
  - 本地存储：Room（minSdk≥24 直接采用），媒体与插图缓存
- 检索与推荐
  - 标签/主题/适龄筛选；离线全文索引（轻量）
- 本地化与无障碍
  - 仅简体中文；字号/对比度/屏幕朗读兼容

三、兼容性与技术栈
- 最低支持：minSdkVersion 24（Android 7.0；如改为 26（8.0）请批示）；targetSdkVersion 35（Android 15）
- 语言：Kotlin + 必要时 Java
- 播放：MediaSession + 前台服务；TTS（TextToSpeech API）；可选 ExoPlayer（音频文件）
- UI：View 体系（RecyclerView/ConstraintLayout）
- 存储：Room/SQLite；DataStore（偏好）
- EPUB：轻量解析器，统一抽象为 Story/Chapter/Asset
- 网络：Retrofit/OkHttp

四、架构与模块
- app：UI 壳与导航、家长设置、主题皮肤（儿童友好）
- core-model：数据模型与校验
- data-store：本地数据库、索引、导入队列
- importer: txt/epub 解析与插图抽取
- playback：TTS 播放器、媒体控制、定时器
- illustrations：插画渲染与轮播
- providers：
  - local-bundled：预置故事集合（assets 打包）
  - remote-feed：拉取远端 JSON 清单/资源
  - generator-agent：调用 agent 接口生成故事（自动发布）

五、数据模型（要点）
- Story{id, title, authors[], ageRange, themes[], values[], language, cover, createdAt}
- Chapter{id, storyId, index, text, illustrations[]}
- Illustration{id, chapterId, index, imageUri, caption}
- PlaybackState{storyId, chapterIndex, positionMs, updatedAt}
- Source{type: local|remote|generated, origin, license}

六、内容与合规
- 预置≥300篇：原创+公有领域改写；统一版权说明与来源标注
- 正向价值观：生成侧提示约束 + 关键词校验（不设人工审核拦截）
- 去重：MinHash/embedding 近似去重，避免大面积重复

七、GitHub Actions 交付流水线
- JDK 17 + Gradle 缓存
- assembleRelease + 上传 Artifact
- 可选签名与自动 Release（以 secrets 提供 keystore 与口令）
- 触发：push/main、PR、手动、tag 发布

八、安装与部署
- 文档包含：
  - GitHub 仓库创建与 secrets 配置（KEYSTORE_BASE64/…）
  - 工作流运行与下载 APK
  - 安卓设备安装（Android 7.0+ 安装来源授权、Android 13+ 权限提示）
  - 首次导入示例（txt/epub）

九、里程碑（建议）
- M1（1周）：原型（本地 50 篇 + TTS + 后台播放 + 进度 + 定时 + txt 导入）
- M2（2周）：epub 导入 + 插画轮播 + 预置 300 篇 + 标签检索
- M3（1周）：远程源 + agent 生成（自动发布）
- M4（1周）：CI/CD + 文档打磨

十、品牌与视觉（建议）
- 主色：天蓝 + 向日黄；辅色：草绿、糖果粉
- 图标：圆角屋子 + 打开的小书 + “墩子”小角色剪影
- 字体：系统默认 + 儿童友好圆角标题字（应用内素材）

十一、待确认
- minSdk 最终取值为 24 或 26（二选一）
- 生成端点：使用现有 agent 网关，或提供后端占位 API

十二、验收标准（摘要）
- 首次安装即含≥300篇可播放/可阅读故事；首页能按适龄和主题筛选
- 导入 txt/epub 成功，章节/插图解析正确
- 后台播放、进度、定时、TTS、插画轮播可用且在重启后进度可恢复
- Actions 能产出可安装 APK；签名可选

附：仓库建议结构
- app/
- core-model/
- data-store/
- importer/
- playback/
- illustrations/
- providers/{local-bundled,remote-feed,generator-agent}/
- assets/stories/
- docs/{INSTALL.md, DEPLOY.md}
- .github/workflows/android.yml
