#@###Ubuntu17.04 jconsole  use java Epoll,not netty Epoll
名称: nioEventLoopGroup-2-1
状态: RUNNABLE
总阻止数: 0, 总等待数: 0

堆栈跟踪: 
sun.nio.ch.EPollArrayWrapper.epollWait(Native Method)
sun.nio.ch.EPollArrayWrapper.poll(EPollArrayWrapper.java:269)
sun.nio.ch.EPollSelectorImpl.doSelect(EPollSelectorImpl.java:93)
sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
   - 已锁定 io.netty.channel.nio.SelectedSelectionKeySet@27a63ba9
   - 已锁定 java.util.Collections$UnmodifiableSet@1f5b4647
   - 已锁定 sun.nio.ch.EPollSelectorImpl@7dc49fec
sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:756)
io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:411)
io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
java.lang.Thread.run(Thread.java:748)

####Ubuntu17.04 jconsole  use netty Epoll
名称: epollEventLoopGroup-2-1
状态: RUNNABLE
总阻止数: 0, 总等待数: 0

堆栈跟踪: 
io.netty.channel.epoll.Native.epollWait0(Native Method)
io.netty.channel.epoll.Native.epollWait(Native.java:114)
io.netty.channel.epoll.EpollEventLoop.epollWait(EpollEventLoop.java:239)
io.netty.channel.epoll.EpollEventLoop.run(EpollEventLoop.java:256)
io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
java.lang.Thread.run(Thread.java:748)

###Windows10 jconsole
名称: nioEventLoopGroup-2-1
状态: RUNNABLE
总阻止数: 0, 总等待数: 0

堆栈跟踪: 
sun.nio.ch.WindowsSelectorImpl$SubSelector.poll0(Native Method)
sun.nio.ch.WindowsSelectorImpl$SubSelector.poll(WindowsSelectorImpl.java:296)
sun.nio.ch.WindowsSelectorImpl$SubSelector.access$400(WindowsSelectorImpl.java:278)
sun.nio.ch.WindowsSelectorImpl.doSelect(WindowsSelectorImpl.java:159)
sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
   - 已锁定 io.netty.channel.nio.SelectedSelectionKeySet@11284a9e
   - 已锁定 java.util.Collections$UnmodifiableSet@23eaefcd
   - 已锁定 sun.nio.ch.WindowsSelectorImpl@1b299963
sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
io.netty.channel.nio.SelectedSelectionKeySetSelector.select(SelectedSelectionKeySetSelector.java:62)
io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:756)
io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:411)
io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:884)
io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
java.lang.Thread.run(Thread.java:745)



