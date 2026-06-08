import { ref, computed } from "vue";

export function useCamera() {
    const scale = ref(1);
    const x = ref(0);
    const y = ref(0);

    let isPanning = false;
    let startX = 0;
    let startY = 0;

    function startPan(e: PointerEvent) {
        isPanning = true;
        startX = e.clientX - x.value;
        startY = e.clientY - y.value;
        (e.currentTarget as HTMLElement).setPointerCapture(e.pointerId);
    }

    function pan(e: PointerEvent) {
        if (!isPanning) return;

        x.value = e.clientX - startX;
        y.value = e.clientY - startY;
    }

    function stopPan() {
        isPanning = false;
    }

    function zoom(delta: number) {
        scale.value = Math.min(4, Math.max(1, scale.value + delta));
    }

    const transform = computed(() => ({
        transform: `translate(${x.value}px, ${y.value}px) scale(${scale.value})`,
    }));

    return {
        scale,
        x,
        y,
        transform,
        startPan,
        pan,
        stopPan,
        zoom,
    };
}