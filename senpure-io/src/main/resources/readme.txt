网关设计思路

两个io服务 client and gateway  和 server and gateway

玩家与client and gateway 保持连接
具体的游戏服务器与server and gateway 保持连接 多个连接通道

游戏服务器启动时向网关注册自己处理的消息id