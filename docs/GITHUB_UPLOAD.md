# 推送到 GitHub 与合并策略

一、准备
- 在 GitHub 创建目标仓库（推荐名称：zidunzi-story-house）
- 获取仓库地址（SSH 或 HTTPS），并为我们赋予 push/PR 权限（至少 Write）

二、本地初始化（如需先在本地聚合）
- 我们将整理当前工作区到临时目录 repo/ 并初始化 git
- 添加远程 origin 指向目标仓库

三、CI 与保护
- 默认分支 main 受保护：
  - 需通过 Actions 构建（android.yml）
  - 至少 1 位审查者批准
  - 仅允许 squash merge

四、合并流程
- 功能在 feature/* 分支开发
- 提 PR → 通过 CI → 审批 → squash merge 入 main
- 发版：打 tag 触发构建与可选签名

五、文件清单（首批推送）
- README.md、BRAND.md、BRIEF.md、ROADMAP.md
- docs/{INSTALL.md, CI_NOTES.md}
- design/*（图标与配色）
- .github/workflows/android.yml
- 代码框架提交后，后续 PR 补全模块

六、注意事项
- Gradle Wrapper 固定 8.5；JDK 17
- 若需 Git LFS（插画/大资源），建议将 assets/illustrations/ 纳入 LFS

待办
- 陛下提供 GitHub 仓库地址与权限后，我们可在 1 个工作日内完成首批推送与合库设置。