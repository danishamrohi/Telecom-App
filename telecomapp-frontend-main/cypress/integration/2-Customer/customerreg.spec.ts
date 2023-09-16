import * as cypress from "cypress";
/// <reference types="cypress" />

describe('land in login page',()=>{
    it('visit the page',()=>{
        cy.visit("http://localhost:4200/login")
    });

    it('should land up in login component',()=>{
    cy.url().should('include','login');
    cy.wait(2000);
    });

    it('should have login form',()=>{
    cy.get('#logintitle').contains('Please Login Here')
    cy.wait(2000);
    })

})


describe('check register button',()=>{

    it('check for register button',()=>{
        cy.get('#customerreg').click();
       cy.wait(2000);
       })

    it('should land up in home',()=>{
    cy.url().should('include','/customer/register');
    cy.wait(2000);
    });

})

describe('Using form to register',()=>{

    it('enter name',()=>{
        cy.get('#name').type("abc")
       cy.wait(2000);
       })
   
       it('enter email',()=>{
        cy.get('#emailId').type("abc@uwo.ca")
       cy.wait(2000);
       })
       
    it('enter user name',()=>{
       cy.get('#userName').type("abc")
      cy.wait(2000);
      })
   
      it('enter password',()=>{
       cy.get('#password').type("123")
      cy.wait(2000);
      })
      
      it('enter phone no',()=>{
        cy.get('#phoneNumber').type("21655500")
       cy.wait(2000);
       })

      it('press submit',()=>{
       cy.get('#regsubmit').click();
      cy.wait(2000);
      })

      it('press login',()=>{
        cy.get('#login').click();
       cy.wait(2000);
       })
   })