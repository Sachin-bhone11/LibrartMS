import { createSlice } from "@reduxjs/toolkit";

const loginSlice = createSlice({
    name:'login',
    initialState: {userId:'', email:'', token:'', role:''},
    reducers: {
        setLogInUser(state, action) {
            console.log(state);
            console.log(action);
            state = {...action.payload};
            console.log(state)

            return state;
        }
    }
})

export const loginActions = loginSlice.actions;

export default loginSlice;