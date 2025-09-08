import { LOGINSUCCESS, LOGOUT } from "../mutation-type"


// 为 store state 声明类型
interface State {
    username?: string,
    token?: string,
    name?: string,
}
export const user = {
    state: () => ({
        username: undefined,
        token: undefined,
        name: undefined,
        // uuid: ''
    }),
    mutations: {
        [LOGINSUCCESS](state: State, payload: State) {
            state.username = payload.username
            state.token = payload.token
            state.name = payload.name
        },
        [LOGOUT](state: State) {
            state.username = undefined
            state.token = undefined
            state.name = undefined
        },
    },
    actions: {

    },
    getters: {
       
    }
}
