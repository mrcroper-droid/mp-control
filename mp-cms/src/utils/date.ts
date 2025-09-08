import moment from "moment";

export default {
    YMD: (arg: string) => {
        return moment(arg).format("YYYY-MM-DD")
    },
    YMDHMS: (arg: string) => {
        return moment(arg).format("YYYY-MM-DD HH:mm:ss")
    }
}