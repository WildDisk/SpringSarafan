<!--<template>-->
<!--    <div>-->
<!--        <i>({{message.id}})</i> {{message.text}}-->
<!--        <span style="position: absolute; right: 0">-->
<!--            <input type="button" value="Edit" @click="edit" />-->
<!--            <input type="button" value="X" @click="del">-->
<!--        </span>-->
<!--    </div>-->
<!--</template>-->
<template>
    <v-card class="my-2">
        <v-card-text title="title">
            <i>({{message.id}})</i>
            {{message.text}}
        </v-card-text>
        <v-card-actions>
            <v-btn value="Edit" @click="edit" icon color="green">
                <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <v-btn @click="del" icon color="green">
                <v-icon>mdi-delete</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        props: ['message', 'editMessage', 'deleteMessage', 'messages'],
        methods: {
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.deleteMessage(this.message)
            }
        }
    }
</script>

<style scoped>

</style>