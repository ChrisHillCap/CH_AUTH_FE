package controllers

import javax.inject.Inject

import forms.LoginForm
import play.api.i18n.MessagesApi
import play.api.mvc._
import play.api.mvc.Results._
import services.LoginService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LoginControllerImpl @Inject()(val loginService: LoginService,
                                    val messagesApi: MessagesApi) extends LoginController

trait LoginController extends AuthController with play.api.i18n.I18nSupport{
  val loginService:LoginService

  def show() = Action.async {implicit request =>
    Future.successful(Ok(views.html.login(LoginForm.form)))
  }
  def submit = Action.async {implicit request =>
    LoginForm.form.bindFromRequest.fold(
      errors => Future.successful(BadRequest(views.html.login(errors))),
        valid => loginService.Login(valid).map { res =>
          res.fold(throw new Exception)(a => if (a) Ok("Logged in").withSession(Security.username -> valid.email) else Ok("not allowed entry"))
        })
  }
}
