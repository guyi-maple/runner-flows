package tech.guyi.component.flows.endpoint.input;

import tech.guyi.component.channel.MessageChannelOption;
import tech.guyi.component.channel.defaults.UdpMessageChannel;
import tech.guyi.component.flows.api.NodeFlowContext;
import tech.guyi.component.flows.api.runner.NodeRunner;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Optional;

/**
 * @author guyi
 * @date 2021/3/19 21:03
 */
public class UdpRunner implements NodeRunner {

    /**
     * 默认端口
     */
    private static final int PORT = 9999;

    /**
     * 端口参数名称
     */
    private static final String PORT_KEY = "port";

    @Override
    public void run(NodeFlowContext context, Object params, Map<String, Object> properties) {
        UdpMessageChannel channel = new UdpMessageChannel();
        channel.option(MessageChannelOption.ON_MESSAGE, context::next);
        int port = Optional.ofNullable(properties.get(PORT_KEY))
                .map(Object::toString)
                .map(Double::parseDouble)
                .map(Double::intValue)
                .orElse(PORT);
        channel.listen(new InetSocketAddress(port));
        context.onStop(ctx -> channel.close());
    }

}
