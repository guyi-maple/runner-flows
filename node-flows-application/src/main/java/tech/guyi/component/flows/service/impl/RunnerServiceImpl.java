package tech.guyi.component.flows.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import tech.guyi.component.flows.NodeRunnerFactory;
import tech.guyi.component.flows.service.RunnerService;
import tech.guyi.component.flows.service.entry.RunnerEntry;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author guyi
 * @date 2021/3/20 16:20
 */
@Service
public class RunnerServiceImpl implements RunnerService, InitializingBean {

    @Resource
    private NodeRunnerFactory factory;

    private List<RunnerEntry> runners;

    @Override
    public void afterPropertiesSet() {
        this.runners = this.factory.runners();
    }


    @Override
    public List<RunnerEntry> all() {
        return this.runners;
    }

}
