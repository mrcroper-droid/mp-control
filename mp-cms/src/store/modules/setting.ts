import { CHANGECOLOR } from "../mutation-type"


// 为 store state 声明类型
interface State {
    themeColor: string,
}
export const setting = {
    state: () => ({
        themeColor: '#409eff',
        // uuid: ''
    }),
    mutations: {
        [CHANGECOLOR](state: State, payload: State) {
            state.themeColor = payload.themeColor
        }
        
    },
    actions: {

    },
    getters: {

    }
}
