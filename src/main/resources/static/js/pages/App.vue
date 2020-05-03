<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>SpringSarafan</v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">{{profile.name}}</span>
            <v-btn v-if="profile" icon color="green" href="/logout">
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-content>
            <v-container v-if="!profile" v-text>
                Необходимо авторизоваться через
                <v-btn raised href="/login">Google</v-btn>
            </v-container>
            <v-container v-if="profile">
                <messages-list/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import {mapState, mapMutations} from 'vuex'
    import MessagesList from '../components/messages/MessageList.vue'
    import {addHandler} from "../util/ws"

    export default {
        components: {
            MessagesList
        },
        computed: mapState(['profile']),
        methods: mapMutations([
            'addMessageMutation',
            'updateMessageMutation',
            'removeMessageMutation'
        ]),
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateMessageMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeMessageMutation(data.body)
                            break
                        default:
                            console.error(`Looks like the event type if unknown "${data.eventType}"`)
                    }
                } else {
                    console.error(`Looks like the object type if unknown "${data.objectType}"`)
                }
            })
        }
    }
</script>

<style>

</style>