<!--<template>-->
<!--    <v-app>-->
<!--        <v-app-bar app>-->
<!--            SpringSarafan-->
<!--            {{profile.name}}&nbsp;-->
<!--            <a href="/logout">Logout</a>-->
<!--        </v-app-bar>-->
<!--        <v-content>-->
<!--            <div v-if="!profile">Необходимо авторизоваться через&ndash;&gt;-->
<!--                <a href="/login">Google</a>-->
<!--            </div>-->
<!--            <div>-->
<!--                <messages-list :messages="messages"/>-->
<!--            </div>-->
<!--        </v-content>-->
<!--    </v-app>-->
<!--</template>-->
<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>SpringSarafan</v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">{{profile.name}}</span>
            <v-btn  v-if="profile" icon color="green" href="/logout">
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-content>
            <v-container v-if="!profile" v-text>
                Необходимо авторизоваться через
                <v-btn raised href="/login">Google</v-btn>
            </v-container>
            <v-container v-if="profile">
                <messages-list :messages="messages"/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import MessagesList from '../components/messages/MessageList.vue';
    import {addHandler} from "./util/ws";
    import {getIndex} from "./util/collections";

    export default {
        components: {
            MessagesList
        },
        data() {
            return {
                messages: frontendData.messages,
                profile: frontendData.profile
            }
        },
        created() {
            addHandler(data => {
                let index = getIndex(this.messages, data.id);
                if (index > -1) {
                    this.messages.splice(index, 1, data)
                } else {
                    this.messages.push(data)
                }
            })
        }
    }
</script>

<style scoped></style>