<template>
    <div>
        <select>
            <option>broth</option>
            <option>butter</option>
            <option>flour</option>
            <option>water</option>
            <option>eggs</option>
            <option>vanilla</option>
    </div>
</template>

<script>
export default {
    props: {
        options: {
            type: Object,
            required: true
        }
    }
}
</script>