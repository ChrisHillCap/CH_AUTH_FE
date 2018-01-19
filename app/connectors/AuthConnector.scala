package connectors

import javax.inject.Inject

import models.Login
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AuthConnectorImpl @Inject()() extends AuthConnector

trait AuthConnector {
 private val validUsers:Map[String,Boolean] = Map("chris@chris.com" -> true,"bob@bob.com" -> false)

  def identifyUser(login: Login):Future[Option[Boolean]] = {
    Future.successful(validUsers.filter(a => a._1 == login.email).headOption.map(_._2))
  }
}
