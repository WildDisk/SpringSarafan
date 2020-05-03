<template>
    <v-layout align-content-space-around justify-start column>
        <message-form :message-attr="message"/>
        <message-row v-for="message in sortedMessages"
                     :key="message.id"
                     :message="message"
                     :edit-message="editMessage"/>
    </v-layout>
</template>

<script>
    import {mapGetters} from 'vuex'
    import MessageRow from './MessageRow.vue'
    import MessageForm from './MessageForm.vue'

    export default {
        components: {
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null
            }
        },
        computed: mapGetters(['sortedMessages']),
        methods: {
            editMessage(message) {
                this.message = message
            },
        }
    }
</script>

<style>

</style>