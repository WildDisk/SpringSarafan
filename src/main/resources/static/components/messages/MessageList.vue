<!--<template>-->
<!--    <div style="position: relative; width: 300px;">-->
<!--        <message-form :messages="messages" :messageAttr="message"/>-->
<!--        <message-row v-for="message in messages"-->
<!--                     :key="message.id"-->
<!--                     :message="message"-->
<!--                     :editMessage="editMessage"-->
<!--                     :deleteMessage="deleteMessage"-->
<!--                     :messages="messages"/>-->
<!--    </div>-->
<!--</template>-->
<template>
    <v-layout align-content-space-around justify-start column>
        <message-form :messages="messages" :message-attr="message"/>
        <message-row v-for="message in sortedMessages"
                     :key="message.id"
                     :message="message"
                     :edit-message="editMessage"
                     :delete-message="deleteMessage"
                     :messages="messages"/>
    </v-layout>
</template>

<script>
    import MessageRow from './MessageRow.vue'
    import MessageForm from './MessageForm.vue'

    export default {
        props: ['messages'],
        components: {
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null
            }
        },
        computed: {
            sortedMessages() {
                return this.messages.sort((a, b) => -(a.id - b.id))
            }
        },
        methods: {
            editMessage(message) {
                this.message = message
            },
            deleteMessage(message) {
                this.$resource('/message{/id}').remove({id: message.id}).then(result => {
                    if (result.ok) {
                        this.messages.splice(this.messages.indexOf(message), 1)
                    }
                })
            }
        }
    }
</script>

<style>

</style>