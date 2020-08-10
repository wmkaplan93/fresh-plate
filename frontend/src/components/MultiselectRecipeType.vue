<template>
    <div id="app">
    <multiselect : options="options" :value="selectedTypes" 
    @input="updateSelected" @search-change="searchQuery" : show-labels="true" :multiple="true" :searchable="true" :close-on-select="true"
    placeholder="Select all types" :custom-label="customLabel" track-by="name"></multiselect>


    </div>    
</template>

<script>
export default {
    components: {
        Multiselect: window.VueMultiselect.default
    },
    data() {
        options: [],
        selectedTypes: []
    }
}.$mount('#app)
</script>