import { ref } from "vue";

export function useInputMode() {
    const mode = ref<"pan" | "draw">("pan");

    function setPan() {
        mode.value = "pan";
    }

    function setDraw() {
        mode.value = "draw";
    }

    return {
        mode,
        setPan,
        setDraw,
    };
}