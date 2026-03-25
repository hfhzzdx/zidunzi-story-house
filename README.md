# 小墩子故事屋（Zidunzi Story House）

本仓库用于 3-8 岁儿童故事类 Android 应用的源码与素材，含 CI 流水线（GitHub Actions 构建 APK）。

核心信息
- 平台：Android（minSdk 24，targetSdk 35）
- Gradle：8.5（wrapper 锁定）
- 主要功能：txt/epub 导入，TTS 语音，后台播放，进度与定时，插画轮播；内置≥300篇故事；支持网络获取与 agent 生成
- 语言：简体中文

目录结构（拟）
- app/
- core-model/
- data-store/
- importer/
- playback/
- illustrations/
- providers/{local-bundled,remote-feed,generator-agent}/
- assets/stories/（预置故事与插画）
- design/（图标与配色）
- docs/（安装、部署、CI、路线图）
- .github/workflows/android.yml（CI 构建）

快速开始（仅 GitHub Actions 构建）
1) 在 GitHub 新建空仓库（Public/Private 均可）
2) 将本项目文件推送至仓库（见 docs/GITHUB_UPLOAD.md）
3) 如需签名发布：在 Settings → Secrets 配置 KEYSTORE_*
4) 触发 Actions → Android CI，下载 APK Artifact

分支与合并策略（建议）
- default 分支：main（受保护）
- 工作分支：feature/*、fix/*、release/*
- 合并策略：优先 squash merge（保持 main 提交历史整洁）
- PR 审查：至少 1 人批准；通过 CI 构建

许可证与内容
- 代码许可证：MIT（可调整）
- 内容：原创/公有领域改写；保留来源说明；生成内容遵循价值观约束与去重

致谢
- 视觉：配色方案A；图标方案3（热气球书屋）
