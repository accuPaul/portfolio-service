package com.mountp.portfolio_service.listeners;

import com.mountp.portfolio_service.models.BaseModel;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MongoListener extends AbstractMongoEventListener<BaseModel> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent<BaseModel> event) {
        super.onBeforeConvert(event);

        Date currentDate = new Date();
        event.getSource().setCreatedAt(currentDate);
        event.getSource().setUpdatedAt(currentDate);
    }
}
