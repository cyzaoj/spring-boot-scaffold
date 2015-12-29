<#assign base=request.contextPath />
<#import "../common/spring.ftl" as spring/>

<!DOCTYPE html>
<html>
<head>
    <!-- Standard Meta -->
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <!-- Site Properities -->
    <title><@spring.message "common.login" /></title>
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/reset.css">
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/site.css">
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/container.css">
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/grid.css">
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/header.css">
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/image.css">
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/menu.css">
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/divider.css">
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/segment.css">
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/form.css">
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/input.css">
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/button.css">
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/list.css">
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/message.css">
    <link rel="stylesheet" type="text/css" href="${base}/bower_components/semantic/components/icon.css">
    <script src="${base}/js/jquery-2.1.4.js"></script>
    <script src="${base}/bower_components/semantic/components/form.js"></script>
    <script src="${base}/bower_components/semantic/components/transition.js"></script>
    <style type="text/css">
        body {
            background-color: #DADADA;
        }

        body > .grid {
            height: 100%;
        }

        .image {
            margin-top: -100px;
        }

        .column {
            max-width: 450px;
        }
    </style>
    <script>
        $(document)
                .ready(function () {
                    $('.ui.form')
                            .form({
                                fields: {
                                    email: {
                                        identifier: 'email',
                                        rules: [
                                            {
                                                type: 'empty',
                                                prompt: 'Please enter your userName'
                                            },
                                            {
                                                type: 'text',
                                                prompt: 'Please enter a valid userName'
                                            }
                                        ]
                                    },
                                    password: {
                                        identifier: 'password',
                                        rules: [
                                            {
                                                type: 'empty',
                                                prompt: 'Please enter your password'
                                            },
                                            {
                                                type: 'length[6]',
                                                prompt: 'Your password must be at least 6 characters'
                                            }
                                        ]
                                    }
                                }
                            })
                    ;
                })
        ;
    </script>
</head>
<body>
<div class="ui middle aligned center aligned grid">
    <div class="column">
        <h2 class="ui teal image header">
        <#--<img src="assets/images/logo.png" class="image">-->
            <div class="content">
                <@spring.message 'project.name' />
            </div>
        </h2>
        <form class="ui large form" action="/login" method="post">
            <div class="ui stacked segment">
                <div class="field">
                    <div class="ui left icon input">
                        <i class="user icon"></i>
                        <input type="text" id="username" name="username" placeholder="<@spring.message 'common.username' />">
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" id="password" name="password" placeholder="<@spring.message 'common.password' />">
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="ui fluid large teal submit button"><@spring.message "common.login" /></div>
            </div>
            <div class="ui error message"></div>
        </form>
    <#--<div class="ui message">
        New to us? <a href="#">Sign Up</a>
    </div>-->
    </div>
</div>
</body>
</html>