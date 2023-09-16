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

describe('Using form to login',()=>{

    it('select user type',()=>{
       cy.get('#usertype').click().get('mat-option').contains('Admin').click();
    cy.wait(2000);
    })
   
    it('enter user name',()=>{
       cy.get('#userName').type("admin")
      cy.wait(2000);
      })
   
      it('enter password',()=>{
       cy.get('#password').type("password")
      cy.wait(2000);
      })
      
      it('press login',()=>{
       cy.get('#loginbutton').click();
      cy.wait(2000);
      })
   })
   
   
   
describe('land in admin dashobard',()=>{  

   it('should land up in admin dashobard',()=>{
   cy.url().should('include','/admin/dashboard');
   cy.wait(2000);
    });
   
})

   
describe('click register button',()=>{

    it('check for register button',()=>{
        cy.get('#csrregister').click();
       cy.wait(2000);
       })

    it('should land up in csr register',()=>{
    cy.url().should('include','/admin/register');
    cy.wait(2000);
    });

})

describe('Using form to register new csr',()=>{

    it('enter csrid',()=>{
        cy.get('#csrId').type("77")
       cy.wait(2000);
       })
       
       it('enter name',()=>{
        cy.get('#name').type("Dave Keel")
       cy.wait(2000);
       })

       it('enter email',()=>{
        cy.get('#email').type("dave@uwo.ca")
       cy.wait(2000);
       })

       it('enter phone no',()=>{
        cy.get('#phoneNo').type("11223344")
       cy.wait(2000);
       })

    it('enter user name',()=>{
       cy.get('#userName').type("dave")
      cy.wait(2000);
      })
   
      it('enter password',()=>{
       cy.get('#password').type("1234")
      cy.wait(2000);
      })

      it('select if admin',()=>{
        cy.get('#admin').click().get('mat-option').contains('No').click();
     cy.wait(2000);
     })

      it('press submit',()=>{
       cy.get('#regsubmit').click();
      cy.wait(2000);
      })

      it('return to dash',()=>{
        cy.get('#backtodash').click();
       cy.wait(2000);
       })
   })

   describe('click export now',()=>{

    it('check for export button',()=>{
        cy.get('#exportcall').click();
       cy.wait(2000);
       })

    it('should land up in admin call reports',()=>{
    cy.url().should('include','/admin/callreports');
    cy.wait(2000);
    });

})

describe('check export functionality',()=>{

    it('export all calls',()=>{
        cy.get('#allcalls').click();
       cy.wait(2000);
       })

       it('enter csr id',()=>{
        cy.get('#csrId').type("75");
       cy.wait(2000);
       })

       it('export id calls',()=>{
        cy.get('#idcalls').click();
       cy.wait(2000);
       })

       it('select renewal',()=>{
        cy.get('#source').click().get('mat-option').contains('Renewal').click();
     cy.wait(2000);
     })

       it('export source calls',()=>{
        cy.get('#sourcecalls').click();
       cy.wait(2000);
       })

       it('enter date',()=>{
        cy.get('#date').type("1999-09-08");
       cy.wait(2000);
       })

       it('export date calls',()=>{
        cy.get('#datecalls').click();
       cy.wait(2000);
       })

       it('enter phone no',()=>{
        cy.get('#phoneNo').type("524163");
       cy.wait(2000);
       })

       it('export phone calls',()=>{
        cy.get('#phonecalls').click();
       cy.wait(2000);
       })

    it('return to admin dash',()=>{
    cy.get('#returndash').click();
    cy.wait(2000);
    });

})

describe('checking nav bar buttons',()=>{
   
    it('check for home button',()=>{
    cy.get('#home').click();
    cy.wait(2000);
    })
    
    it('check for dashboard button',()=>{
     cy.get('#admindash').click();
    cy.wait(2000);
    })
 })
 
 describe('check logout button',()=>{
 
     it('check for logout button',()=>{
         cy.get('#logout').click();
        cy.wait(2000);
        })
 
     it('should land up in home',()=>{
     cy.url().should('include','/home');
     cy.wait(2000);
     });
 
 })