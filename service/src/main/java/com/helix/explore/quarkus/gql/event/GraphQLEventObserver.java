package com.helix.explore.quarkus.gql.event;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.smallrye.graphql.api.Context;
import io.smallrye.graphql.execution.event.InvokeInfo;
import io.smallrye.graphql.schema.model.Operation;
import io.smallrye.graphql.spi.EventingService;
import lombok.extern.slf4j.Slf4j;

import javax.json.bind.Jsonb;
import java.util.Map;

@Slf4j
public class GraphQLEventObserver
        implements EventingService {

    @Override
    public String getConfigKey() {
        return null;
    }

    @Override
    public GraphQLSchema.Builder beforeSchemaBuild(GraphQLSchema.Builder builder) {
        return EventingService.super.beforeSchemaBuild(builder);
    }

    @Override
    public Operation createOperation(Operation operation) {
        return EventingService.super.createOperation(operation);
    }

    @Override
    public Map<String, Jsonb> overrideJsonbConfig() {
        return EventingService.super.overrideJsonbConfig();
    }

    @Override
    public GraphQL.Builder beforeGraphQLBuild(GraphQL.Builder builder) {
        return EventingService.super.beforeGraphQLBuild(builder);
    }

    @Override
    public void beforeExecute(Context context) {
        log.info("GraphQL Before Execute Log.");
        EventingService.super.beforeExecute(context);
    }

    @Override
    public void afterExecute(Context context) {
        EventingService.super.afterExecute(context);
    }

    @Override
    public void errorExecute(String executionId, Throwable t) {
        EventingService.super.errorExecute(executionId, t);
    }

    @Override
    public void beforeDataFetch(Context context) {
        EventingService.super.beforeDataFetch(context);
    }

    @Override
    public void beforeInvoke(InvokeInfo invokeInfo) throws Exception {
        EventingService.super.beforeInvoke(invokeInfo);
    }

    @Override
    public void afterDataFetch(Context context) {
        EventingService.super.afterDataFetch(context);
    }

    @Override
    public void errorDataFetch(String executionId, Throwable t) {
        EventingService.super.errorDataFetch(executionId, t);
    }

}
