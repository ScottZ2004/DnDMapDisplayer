import { ref, nextTick} from "vue";

export function useFog() {
    const canvasRef = ref<HTMLCanvasElement | null>(null);
    let ctx: CanvasRenderingContext2D | null = null;

    async function init() {
        await nextTick();

        const canvas = canvasRef.value;
        if (!canvas) return;

        const ctxLocal = canvas.getContext("2d");
        if (!ctxLocal) return;

        ctx = ctxLocal;

        canvas.width = canvas.clientWidth;
        canvas.height = canvas.clientHeight;

        ctx.fillStyle = "black";
        ctx.fillRect(0, 0, canvas.width, canvas.height);

        ctx.globalCompositeOperation = "destination-out";
    }

    function erase(x: number, y: number, radius: number) {
        if (!ctx) return;

        ctx.beginPath();
        ctx.arc(x, y, radius, 0, Math.PI * 2);
        ctx.fill();
    }

    return {
        canvasRef,
        init,
        erase,
    };
}