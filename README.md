# 准备kafka
    启动zookeeper -- zkServer.sh start
    启动kafka MQ  -- bin/kafka-server-start.sh config/server.properties
    
    本地虚拟机测试：
        vi server.properties
        去掉下面两行注释
        listeners=PLAINTEXT://:9092
        advertised.listeners=PLAINTEXT://host:9092(虚拟机ip--192.168.1.132)
    
    Kafka tool 连接并查看topic partition offset
    
# 核心概念
    https://www.cnblogs.com/judesheng/p/10621682.html
# 生产者
    向Kafka服务端写入数据
## 涉及到的组件
    生产者     KafkaProducer（客户端--线程安全的） 
    拦截器     ProducerInterceptors&ProducerInterceptor
    元数据     Metadata
                Cluster
                    Node
                    TopicPartition
                    PartitionInfo
    序列化器    Serializer&Deserializer
    分区器      Partitioner
    记录收集器  RecordAccumulator(缓存)
                    RecordBatch
                    MemoryRecords(Wrap NIO ByteBuffer)
                    BufferPool
    网络处理器   NetworkClient
                    消息发送器   Sender（线程调度）
                                    请求响应协议 ProduceRequest & ProduceResponse
                    socket通信   Selector
                    元数据更新器  MetadataUpdater
                    ......
                    
    消息格式参考：https://kafka.apachecn.org/documentation.html#messageformat
## 流程图
# 消费者
    从kafka服务端拉取消息，保证业务逻辑与消息消费的一致性
## 涉及到的组件
    消费者          KafkaConsumer(非线程安全的--订阅、消费)
    消息拉取器      Fetcher
                        请求响应协议 FetchRequest & FetchResponse
    订阅状态        SubscriptionState
                        SubscriptionType 订阅Topic的模式
                        TopicparitionState TopicParition的消费状态
    分区分配器      PartitionAssignor(2.4弃用 自定义分配器使用org.apache.kafka.clients.consumer.ConsumerPartitionAssignor)
    消费协调者      ConsumerCoordinator(消费者变动以及服务端最近的offset)
                        再均衡 Rebalance
    网络处理        ConsumerNetworkClient(心跳任务、网络IO)
                        心跳检测 Heartbeat
                        请求处理器 *Handler
                        监听器 RequestFuture
# spring-kafka
    https://spring.io/projects/spring-kafka
# 参考资料
* Apache Kafka源码剖析
* Kafka源码解析与实战   
* kafka权威指南
* 深入理解kafka：核心设计与实践原理
* 分布式消息中间件实践 
* https://kafka.apachecn.org/documentation.html
