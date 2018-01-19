package services

import javax.inject.Inject

import connectors.AuthConnector
import models.Login

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LoginServiceImpl @Inject()(val authConnector: AuthConnector) extends LoginService

trait LoginService {

  val authConnector :AuthConnector

  def Login(login:Login):Future[Option[Boolean]] = {
    authConnector.identifyUser(login)
  }
}
