package contracts.shouldPublishUserCreated

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Should publish user created event")
    label("shouldPublishUserCreated")

    input {
        triggeredBy("shouldPublishUserCreated()")
    }

    outputMessage {
        sentTo("user-topic")
        body(
                id: uuid()
        )
    }
}