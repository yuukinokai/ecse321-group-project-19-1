<template>
<div class="hello">
    <div class="site-section site-hero">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-10">
                    <h1 class="d-block mb-4" data-aos="fade-up" data-aos-delay="200"> <img src="../assets/nameLogo.svg" style="width:400px"> {{loginType}}</h1>
                    <br>
                    <br>
                    <span class="d-block mb-5 caption" data-aos="fade-up" data-aos-delay="300">ECSE 321 Group 19</span>                    
                    <span class="d-block mb-5 caption" data-aos="fade-up" data-aos-delay="300">BRODEUR Max</span>
                    <span class="d-block mb-5 caption" data-aos="fade-up" data-aos-delay="300">DENG Sophie</span>
                    <span class="d-block mb-5 caption" data-aos="fade-up" data-aos-delay="300">ELKHOURY Carl</span>
                    <span class="d-block mb-5 caption" data-aos="fade-up" data-aos-delay="300">KABA Andre</span>
                    <span class="d-block mb-5 caption" data-aos="fade-up" data-aos-delay="300">ZHOU Mia</span>
                </div>
            </div>
        </div>
    </div>
    <div class="site-section">
        <div class="container">
            <div class="row mb-5">
                <div class="col-lg-4" data-aos="fade-up" data-aos-delay="100">
                    <div class="site-section-heading">
                        <h2>Scope of Project:</h2>
                    </div>
                </div>
                <div class="col-lg-5 mt-5 pl-lg-5" data-aos="fade-up" data-aos-delay="200">
                    <p>The Co-Operator application is designed to seamlessly integrate a co-op program into
                        the McGill software engineering degree. The application shall allow a mixture of McGill
                        students, staff and prospective employers to interact in order efficiently keep track of
                        students progress in the program. The Co-Operator application provides a hub for is a tool
                        to help McGill track student progress and administrate the system. Students involved in the
                        program will be taking multiple semesters to work in the industry, aptly named "Co-Op semesters".
                        The scope of the software engineering group project of the Winter 2019 offering of ECSE321 is to
                        develop the software system for managing co-op terms for such co-op program</p>
                    <p>Our application supports the scenarios described in the user story for the Co-Op Academic 
                      program manager and the Co-Op Term Instructor. Additionally this application is designed to 
                      integrate with the viewpoints of other stake-holders created by seperate teams via service 
                      calls. All functionality of the system is to be accessible via the web frontend for 
                      respective stakeholders. In addition, a mobile (Android) frontend allows easy execution of 
                      the most important functionality for the givenstakeholder having both read and write access 
                      to the backend via RESTful service call.</p>
                    <h2>Project Links</h2>
                    <ul>
                        <li><a href="https://github.com/McGill-ECSE321-Winter2019/ecse321-group-project-19-1/wiki" target="_blank">Project WIKI</a></li>
                        <li><a href="https://github.com/McGill-ECSE321-Winter2019/ecse321-group-project-19-1" target="_blank">Github Repository</a></li>
                        <br>
                        <li><a href="http://vuejs-templates.github.io/webpack/" target="_blank">Docs for This Template</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script src="./javascript/Hello.js"></script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style scoped>
h1,
h2 {
    font-weight: normal;
}

ul {
    list-style-type: none;
    padding: 0;
}

li {
    display: inline-block;
    margin: 0 10px;
}

a {
    color: #42b983;
}
</style>
