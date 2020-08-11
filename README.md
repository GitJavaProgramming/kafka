# 准备kafka
    启动zookeeper -- zkServer.sh start
    启动kafka MQ  -- bin/kafka-server-start.sh config/server.properties
# 核心概念
    https://www.cnblogs.com/judesheng/p/10621682.html
# 生产者
    向Kafka服务端写入数据
## 涉及到的组件
    生产者     KafkaProducer（客户端） 
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
       
# 参考资料
* Apache Kafka源码剖析
* Kafka源码解析与实战   
* kafka权威指南
* 深入理解kafka：核心设计与实践原理
* 分布式消息中间件实践 
* https://kafka.apachecn.org/documentation.html
