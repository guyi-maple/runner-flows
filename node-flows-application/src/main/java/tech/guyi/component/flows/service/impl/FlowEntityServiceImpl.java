package tech.guyi.component.flows.service.impl;

import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.guyi.component.flows.db.entity.FlowEntity;
import tech.guyi.component.flows.db.repository.FlowEntityRepository;
import tech.guyi.component.flows.flow.Flow;
import tech.guyi.component.flows.service.FlowEntityService;
import tech.guyi.component.flows.service.NodeEntityService;
import tech.guyi.component.flows.service.entry.FlowEntry;

import javax.annotation.Resource;

/**
 * @author guyi
 * @date 2021/3/19 22:38
 */
@Service
public class FlowEntityServiceImpl implements FlowEntityService {

    @Getter
    @Resource
    private FlowEntityRepository repository;

    @Resource
    private NodeEntityService nodeEntityService;

    @Override
    public Class<FlowEntity> entityClass() {
        return FlowEntity.class;
    }

    @Override
    @Transactional
    public FlowEntry save(FlowEntry entry){
        entry.setHeader(this.nodeEntityService.save(entry.getHeader()));
        FlowEntity entity = this.autoSave(entry.to());
        entity.setHeader(entry.getHeader().getId());
        entry.setId(entity.getId());
        return entry;
    }

    @Override
    public FlowEntry getEntry(FlowEntity entity) {
        FlowEntry entry = FlowEntry.from(entity);
        entry.setHeader(this.nodeEntityService.getEntry(entity.getHeader()));
        return entry;
    }

    @Override
    public Flow getFlow(FlowEntry entry) {
        Flow flow = new Flow();
        BeanUtils.copyProperties(entry,flow);
        flow.setHeader(this.nodeEntityService.getNode(entry.getHeader()));
        return flow;
    }

}
