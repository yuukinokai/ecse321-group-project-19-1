<template>
<div id="id">

    <div class="site-section site-hero inner">
            <div class="container" align="center">
                <h2 align="center" class="page-title" >Ti Students Selection</h2>
                        <div id="table">
                            <b-table class="info-table"  striped hover :items= "students" :fields= "fields" selectable select-mode="single" @row-selected="studentSelection"/>
                        </div>
            </div>
        </div>

</div>
</template>

<script src="./javascript/tiStudent.js">
</script>
