<template>
<div id="app">
    <div class="site-wrap" style="height:100%;">
        <div class="site-mobile-menu">
            <div class="site-mobile-menu-header">
                <div class="site-mobile-menu-close mt-3">
                    <span class="icon-close2 js-menu-toggle"></span>

                </div>
            </div>
            <div class="site-mobile-menu-body">
            </div>
        </div>

        <header class="site-navbar py-3" role="banner">
            <div class="container-fluid">
                <div class="row align-items-center">
                    <div class="col-11 col-xl-2">
                        <h1 class="mb-0">
                            <img v-if="loggedIn" src="./assets/nameLogo.svg" style="width:200px">
                        </h1>
                    </div>
                    <div class="col-12 col-md-10">
                        <nav class="site-navigation position-relative text-right" role="navigation">
                            <ul class="site-menu js-clone-nav mx-auto">
                                <li >
                                    <router-link :to="{name: 'Home'}">Home</router-link>
                                </li>
                                <li v-if="isPM">
                                    <router-link :to="{name: 'PmStudent'}">Students</router-link>
                                </li>
                                <li v-if="isPM">
                                    <router-link :to="{name: 'Courses'}">Courses</router-link>
                                </li>
                                <li v-if="isPM">
                                    <router-link :to="{name: 'Coops'}">Coops</router-link>
                                </li>
                                <li v-if="isTI">
                                    <router-link :to="{name: 'TiStudent'}">Students</router-link>
                                </li>
                                <li v-if="!loggedIn">
                                    <router-link :to="{name: 'Register'}">Register</router-link>
                                </li>
                                <li v-if="!loggedIn">
                                    <router-link :to="{name: 'Login'}">Login</router-link>
                                </li>
                                <li v-if="loggedIn">
                                    <router-link :to="{name: 'Logout'}">Logout</router-link>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </header>

        <router-view></router-view>

        <footer class="site-footer">
            <div class="container">

                <div class="text-center">
                    <div class="border-top pt-5">
                        <p>
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            Copyright &copy;
                            All rights reserved | This template is made with
                            <i class="icon-heart text-primary" aria-hidden="true"></i> by
                            <a href="https://colorlib.com" target="_blank">Colorlib</a>
                            | @Authors BRODEUR Max, DENG Sophie, ELKHOURY Carl, KABA Andre, ZHOU Mia
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        </p>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
</template>

<script src="./components/javascript/App.js"></script>

<style>
@import url(https://fonts.googleapis.com/css?family=Roboto+Mono);
@import 'style/fonts/icomoon/style.css';
@import 'style/css/bootstrap.min.css';
@import 'style/css/magnific-popup.css';
@import 'style/css/owl.theme.default.min.css';
@import 'style/css/bootstrap-datepicker.css';
@import 'style/fonts/flaticon/font/flaticon.css';
@import 'style/css/aos.css';
@import 'style/css/style.css';
</style>
